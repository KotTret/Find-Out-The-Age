import { Component } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-name',
  templateUrl: './name.component.html',
  styleUrls: ['./name.component.css']
})


export class NameComponent {

  name="";

  constructor(private router: Router) {
  }

  ngOnInit(): void {

  }

  openDataPage() {
    this.router.navigate(['/data'], {queryParams: {name: this.name}})
  }
}
