import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-data',
  templateUrl: './data.component.html',
  styleUrls: ['./data.component.css']
})
export class DataComponent implements OnInit {

  name = "";
  age = 0;
  popularName = "";
  count = 0;
  oldName = "";
  oldAge = 0;

  constructor(private httpClient: HttpClient,
              private router: Router,
              private activatedRoute: ActivatedRoute) {

  }

  ngOnInit(): void {

    this.activatedRoute.queryParams.subscribe(params => {
      this.name = params['name'];
    });


    const data = {name: this.name};
    const body = JSON.stringify(data);

    this.httpClient.post<any>(environment.backendURL + "/data", body, {
      headers: {
        'Content-Type': 'application/json'
      }
    }).subscribe(
      {
        next: ((response: any) => {

          console.log(response);
          console.log(response);

          this.age = response.age;
        }),

        error: (error => {
          this.name = "Error: Имя указано неверно";
          this.age = -1;
          console.log(error)
        })
      }
    );
  }


  logout() {
    this.router.navigate(['/new'])
  }


  requestBackend() {
    this.httpClient.get<any>(environment.backendURL + "/stats", {params:{'name': this.name}}).subscribe(
      {
        next: ((response: any) => {

          console.log(response);
          this.popularName = response.popularName;
          this.count = response.count;
          this.oldName = response.oldName;
          this.oldAge = response.oldAge;

        }),

        error: (error => {
          console.log(error)
        })
      }
    );

  }
}
