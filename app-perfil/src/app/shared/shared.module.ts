import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MensagemComponent } from './mensagem/mensagem.component';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    MensagemComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  exports: [
    MensagemComponent
  ]
})
export class SharedModule { }
