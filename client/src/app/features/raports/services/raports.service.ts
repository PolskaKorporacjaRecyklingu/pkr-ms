import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {RaportUrt, RaportUrtDTOToRaportUrtAdapter, RaportUrtToRaportUrtDTOAdapter} from "../models/RaportURT.model";
import {catchError, map, Observable, of, throwError} from "rxjs";
import {RaportURTGet} from "../models/getRaportURT.model";
import {RaportURTPost} from "../models/postRaportURT.model";

@Injectable({
  providedIn: 'root'
})
export class RaportsService {
  private apiUrl = 'http://exampleUrl'
  constructor(
    private http: HttpClient,
    private dtoToRaportURTAdapter: RaportUrtDTOToRaportUrtAdapter,
    private raportURTToDroAdapter: RaportUrtToRaportUrtDTOAdapter
    ) { }
  private handleError(error: HttpErrorResponse) {
    return throwError(() => new Error('Something bad happened; please try again later.'));
  }

  fetchURTRaport(): Observable<RaportUrt> {
      return this.http.get<RaportURTGet>(this.apiUrl).pipe(
        map(response  => this.dtoToRaportURTAdapter.adapt(response))
      )
  }

  postURTRaport(raport: RaportUrt) {
    let raportPost = this.raportURTToDroAdapter.adapt(raport)
    return this.http.post<RaportURTGet>(this.apiUrl, raportPost).pipe(
      map(response  => this.dtoToRaportURTAdapter.adapt(response)),
      catchError(this.handleError)
    )
  }
}
