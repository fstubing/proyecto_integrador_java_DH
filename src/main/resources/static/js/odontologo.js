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

let editId = document.getElementById("editId");
const updateOdontolgo = (id) => {
  //  for (let i of td) {
   //   i.classList.remove("edit-dom");
  //  }

   // document.getElementById(`idDom${id}`).classList.add("edit-dom");

    formEditOdontologo.classList.remove("d-none");

    editId.value = id;
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

let formAddOdontologo = document.getElementById("formAddOdontologo");

formAddOdontologo.addEventListener("submit", (event) => {
  event.preventDefault();

  const formData = {
    matricula: document.querySelector("#matricula").value,
    nombre: document.querySelector("#nombre").value,
    apellido: document.querySelector("#apellido").value,
  };
  //invocamos utilizando la función fetch la API peliculas con el método POST que guardará
  //la película que enviaremos en formato JSON
  const url = "/odontologo";
  const settings = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(formData),
  };

  fetch(url, settings)
    .then((response) => response.json())
    .then((data) => {
      alert("Odontolgo agregado correctamente")
      location.reload();
    })
    .catch((error) => {
      console.log(error);
    });
});

let formEditOdontologo = document.getElementById("formEditOdontologo");

formEditOdontologo.addEventListener("submit", (event) => {
  event.preventDefault();
  let editId = document.querySelector('#editId').value;

  const formData = {
      id: document.querySelector('#editId').value,
      matricula: document.querySelector('#editMatricula').value,
      nombre: document.querySelector('#editNombre').value,
      apellido: document.querySelector('#editApellido').value,

  };


  const url = '/odontologo/'+editId;
  const settings = {
      method: 'PUT',
      headers: {
          'Content-Type': 'application/json',
      },
      body: JSON.stringify(formData)
  }
    fetch(url,settings)
    .then(response => response.json())
    .then((data) => {
          alert("Odontolgo modificado correctamente")
          location.reload();
        })
        .catch((error) => {
          console.log(error);
        });
});


btnCancelEdit.addEventListener("click", (e) => {
  e.preventDefault();
  formEditPaciente.classList.add("d-none");
  location.reload()
});