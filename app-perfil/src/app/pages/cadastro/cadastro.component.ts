import { Component, OnInit } from '@angular/core';
import { UserService } from '../usuarios.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css'],
})
export class CadastroComponent implements OnInit {
  Formulario: FormGroup = new FormGroup({});

  isEdicao: boolean = false;

  id: number = -1;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.Formulario = this.formBuilder.group({
      nome: [null, [Validators.required]],
      rua: [null, [Validators.required]],
      numero: [null, [Validators.required]],
      cep: [null, [Validators.required]],
    });

    this.activatedRoute.params.subscribe((parametros) => {
      console.log(parametros);

      if (parametros.id) {
        console.log(`edição id ${parametros.id}`);

        this.isEdicao = true;
        this.id = parametros.id;

        this.userService.getOne(parametros.id).subscribe((dadosUsuarios) => {
          console.log(dadosUsuarios);
          this.Formulario.patchValue(dadosUsuarios);
        });
      } else {
        console.log(`criação`);
        this.isEdicao = false;
      }
    });
  }
  Enviar() {
    if (this.isEdicao == false) {
      this.userService.save(this.Formulario.value).subscribe((data) => {
        console.log(data);

        this.router.navigate(['/usuarios']);
      });
    } else {
      this.userService
        .update(this.id, this.Formulario.value)
        .subscribe((data) => {
          console.log(data);
          this.router.navigate(['/usuarios']);
        });
    }
  }
}
