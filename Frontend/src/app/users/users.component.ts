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

  private _listFilterName: string = '';
  private _listFilterEmail: string = '';
    get listFilterName(): string{
      return this._listFilterName;
    }
    set listFilterName(value: string){
      this._listFilterName = value;
      console.log('In setter:', value);
      this.filteredUsers = this.performFilter(value, 1);
    }
    get listFilterEmail(): string{
      return this._listFilterEmail;
    }
    set listFilterEmail(value: string){
      this._listFilterEmail = value;
      console.log('In setter:', value);
      this.filteredUsers = this.performFilter(value, 2);
    }

    filteredUsers: IUser[] = [];
    users: IUser[] = [];

    constructor(private userService: UserService) {}

    performFilter(filterBy: string, numberOfOption: number): IUser[] {
      filterBy = filterBy.toLocaleLowerCase();

      if(numberOfOption === 1)
        return this.users.filter((user: IUser) => user.name.toLocaleLowerCase().includes(filterBy));
      else 
        return this.users.filter((user: IUser) => user.email.toLocaleLowerCase().includes(filterBy));
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
