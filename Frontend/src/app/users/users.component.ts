import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from "rxjs";
import { IUser } from "./user";
import { UserService } from "./users.service";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit, OnDestroy {
  pageTitle: string = 'Buzz Street Employees';
  errorMessage: string = '';
  sub!: Subscription;

  private _listFilter: string = '';
    get listFilter(): string{
      return this._listFilter;
    }
    set listFilter(value: string){
      this._listFilter = value;
      console.log('In setter:', value);
      this.filteredUsers = this.performFilter(value);
    }

    filteredUsers: IUser[] = [];
    users: IUser[] = [];

    constructor(private userService: UserService) {}

    performFilter(filterBy: string): IUser[] {
      filterBy = filterBy.toLocaleLowerCase();
      return this.users.filter((user: IUser) =>
      user.name.toLocaleLowerCase().includes(filterBy));
    }

    ngOnInit(): void {
      this.sub = this.userService.getUsers().subscribe({
        next: users => {
          this.users = users;
          this.filteredUsers = this.users;
        },
        error: err => this.errorMessage = err
      });
    }

    ngOnDestroy(): void { this.sub.unsubscribe(); }
}
