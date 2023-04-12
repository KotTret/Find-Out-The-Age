import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-data',
  templateUrl: './data.component.html',
  styleUrls: ['./data.component.css']
})
export class DataComponent implements OnInit {

  name = "test name";

  disabled = false;

  constructor() {
  }

  ngOnInit(): void {
  }

  changeName(): void {
    this.name = "XPXPXPP"
  }

  changeDisabled() {
    this.disabled = !this.disabled;
  }
}
