import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { usuariosModel } from './usuarios.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) { }

  getAll(){
    return this.httpClient.get<usuariosModel[]>(`${environment.api_url}/usuarios`);
  }

  save(usuarioObj: usuariosModel){
    return this.httpClient.post<usuariosModel>(`${environment.api_url}/usuarios`, usuarioObj);
  }

  delete(id : number){
    return this.httpClient.delete(`${environment.api_url}/usuarios/${id}`);
  }

  getOne(id : number){
    return this.httpClient.get<usuariosModel>(`${environment.api_url}/usuarios/${id}`);
  }

  update(id: number, usuarioObj: usuariosModel){
    return this.httpClient.patch<usuariosModel>(`${environment.api_url}/usuarios/${id}`, usuarioObj);
  }
}
