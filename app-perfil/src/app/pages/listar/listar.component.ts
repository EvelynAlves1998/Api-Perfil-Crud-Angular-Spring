import { Component, OnInit} from '@angular/core';
import { ActivatedRoute,} from '@angular/router';
import { UserService } from '../usuarios.service';
import { usuariosModel } from '../usuarios.model';

@Component({
  selector: 'app-listar',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.css']
})
export class ListarComponent implements OnInit {

  usuarios! : usuariosModel[] ;

  constructor(
    private activatedRoute: ActivatedRoute,
    private UserService: UserService

  ) {
  }

  ngOnInit(): void {

    this.getAll();

    this.activatedRoute.params.subscribe(
      (data) => {
        console.log(data);
      }
    );
  }

  DeletarUsuario(id: number){
    this.UserService.delete(id)
      .subscribe(
        ()=>{
          console.log(`Registro deletado id:${id}`);
          this.getAll();
        }
      );
  }

  private getAll(){

    this.UserService.getAll()
    .subscribe(
      (data) => {
        console.log(data);
        this.usuarios = data;
      }
    );

  }

}
