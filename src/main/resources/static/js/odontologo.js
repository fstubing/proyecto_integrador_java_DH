window.addEventListener('load', () => {

      const url = '/odontologo';
      const settings = {
        method: 'GET',
        mode: 'no-cors',
        headers: {
            'accept': 'application/json',
           'Content-Type': 'application/json',
          }
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {
      console.log(data)
        const tableBody = document.getElementById("tbody");
        let plantilla = ''
        data.forEach((odontologo) => {
            let row = `
             <tr>
                <th scope="row">${odontologo.id}</th>
                <td>${odontologo.matricula}</td>
                <td>${odontologo.nombre}</td>
                <td>${odontologo.apellido}</td>
                <td><button onclick=updateOdontolgo(${odontologo.id}) class="btn btn-warning">Modificar</button></td>
                <td><button onclick=deleteOdontolgo(${odontologo.id}) class="btn btn-danger">Eliminar</button></td>
             </tr>
            `
            plantilla+=row
        })

        tableBody.innerHTML = plantilla


      });

})

const updateOdontolgo = (id) => {

}

const deleteOdontolgo = (id) => {
   const url = `/odontologo/${id}`;
         const settings = {
           method: 'DELETE',
           headers: {
              'accept': 'application/json',
              'Content-Type': 'application/json',
             }
         }

         fetch(url,settings)
         .then(response => console.log(response))
   alert("odontologo borrado")
   location.reload()
}