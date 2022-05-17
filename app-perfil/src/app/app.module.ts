import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
// import { HomeComponent } from './pages/home/home.component';
// import { ListarComponent } from './pages/listar/listar.component';
// import { CadastroComponent } from './pages/cadastro/cadastro.component';
import { PagesModule } from './pages/pages.module';

@NgModule({
  declarations: [
    AppComponent,
    // HomeComponent,
    // ListarComponent,
    // CadastroComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    PagesModule,

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
