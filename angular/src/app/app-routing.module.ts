import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DataComponent} from "./data/data.component";
import {NameComponent} from "./name/name.component";

const routes: Routes = [

  {path: '', component: NameComponent},
  {path: 'new', component: NameComponent},
  {path: 'data', component: DataComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
