import { HttpClient } from "@angular/common/http";
import { environment as env } from "src/environments/environment";
import { Observable } from "rxjs";
import { IUser } from "src/models/data";

export class RestServiceUser {
  private static _instance: RestServiceUser;
  constructor(private http: HttpClient) { }

  static getInstance(http: HttpClient): RestServiceUser{
    if(this._instance == null){
      this._instance = new RestServiceUser(http);
    }
    return this._instance;
  }

  hasProfile$(sub: string): Observable<IUser> {
    return this.http.get<IUser>(env.baseApiUrl + "users/" + sub);
  }

  async addUserAsync(user: IUser) {
    this.http.post<IUser>(env.baseApiUrl + "users/" + user.username, null)
      .subscribe();
  }

  getUser(id: string): Observable<IUser> {
    return this.http.get<IUser>(env.baseApiUrl + "Users/" + id);
  }
}
