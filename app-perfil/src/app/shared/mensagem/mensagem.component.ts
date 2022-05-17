import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-mensagem',
  templateUrl: './mensagem.component.html',
  styleUrls: ['./mensagem.component.css']
})
export class MensagemComponent implements OnInit {

  @Input()
  controles: any;

  @Input()
  mensagem : string = 'Erro ao preencher campo' ;

  @Input()
  debug : boolean = false;

  constructor() { }

  ngOnInit(): void {
  }

}
