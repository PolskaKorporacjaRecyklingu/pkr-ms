import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpParams} from "@angular/common/http";
import {catchError, map, Observable, of, throwError} from "rxjs";
import {RaportURTGet} from "../models/getRaportURT.model";
import {RaportURTPost} from "../models/postRaportURT.model";
import {environment} from "../../../../environments/environment";
import {GetRaportUrtToRaportUrt, RaportURT, RaportUrtToPostRaportUrt} from "../models/RaportURT.model";

@Injectable({
  providedIn: 'root'
})
export class RaportsService {
  private apiUrl = environment.apiUrl
  constructor(
    private http: HttpClient,
    private raportUrtToPost: RaportUrtToPostRaportUrt,
    private getRaportToRaportUrt: GetRaportUrtToRaportUrt
    ) { }
  private handleError(error: HttpErrorResponse) {
    return throwError(() => new Error('Something bad happened; please try again later.'));
  }

  fetchURTRaport(reportId: number): Observable<RaportURT> {
      return this.http.get<RaportURTGet>(this.apiUrl + '/reports/v1/fetch', {
        params: new HttpParams().set('urtReportId', reportId)
      }).pipe(
        map(response  => this.getRaportToRaportUrt.adapt(response))
      )
  }

  postURTRaport(raport: RaportURT) {
    let raportPost = this.raportUrtToPost.adapt(raport)
    return this.http.post<RaportURTGet>(this.apiUrl + '/reports/v1/create', raportPost).pipe(
      catchError(this.handleError)
    )
  }
}
