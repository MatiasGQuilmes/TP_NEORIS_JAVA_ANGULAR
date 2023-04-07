import { FormControl } from "@angular/forms";

export class validadoresEspeciales{
    public static validacionFechaIngreso(element: FormControl){
          
        let texto = element.value;


        let invalido: boolean = false;
        
        let maximo: Date= new Date(texto);
      
        invalido = (maximo > new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDay()+2)) || maximo < (new Date(new Date().getFullYear(), new Date().getMonth()-5, new Date().getDay()))

        return invalido ? "fecha de ingreso invalido ": null;
        
    }

    public static validacionFechaNacimiento(element: FormControl){
          
        let texto = element.value;
  

        let invalido: boolean = false;
        
        let maximo: Date= new Date(texto);
      
        invalido = (maximo > new Date(new Date().getFullYear()-18, new Date().getMonth(), new Date().getDay()+2)) || maximo < (new Date(new Date().getFullYear()-110, new Date().getMonth(), new Date().getDay()))

        return invalido ? "fecha de nacimiento invalido ": null;
        
    }
}