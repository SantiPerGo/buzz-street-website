import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { catchError, Observable, tap, throwError } from "rxjs";
import { IUser } from "./user";

@Injectable({
    providedIn: 'root'
})
export class UserService {
    private userUrl = '/api/users';
    private getUserUrl = '/api/users/getUser';

    constructor(private http: HttpClient) {}

    getUsers(): Observable<IUser[]> {
      return this.http.get<IUser[]>(this.userUrl).pipe(
        tap(data => console.log('All', JSON.stringify(data))),
        catchError(this.handleError)
      );
    }

    private handleError(err: HttpErrorResponse) {
      // In a real app, we may send the server to some remote logging infrastructure
      // insted of just logging it to the console
      let errorMessage = '';

      // A client-side or network error ocurred. Handle it accordingly
      if(err.error instanceof ErrorEvent) {
        errorMessage = `An error occurred: ${err.error.message}`;
      }
      // The backend returned an unsuccesful response code
      // The response body may contain clues as to what went wrong
      else {
        errorMessage = `Server returned code: ${err.status}, error message is: ${err.message}`;
      }
      console.error(errorMessage);
      return throwError(()=>errorMessage);
    }
}