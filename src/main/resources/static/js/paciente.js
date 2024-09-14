window.addEventListener('load', () => {

      const url = '/paciente';
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
        data.forEach((paciente) => {
            let domi = paciente.domicilio
            let row = `
             <tr>
                <th scope="row">${paciente.id}</th>
                <td>${paciente.cedula}</td>
                <td>${paciente.nombre}</td>
                <td>${paciente.apellido}</td>
                <td>${paciente.email}</td>
                <td>${paciente.fechaIngreso}</td>
                <td>${domi.calle} ${domi.numero} ${domi.localidad} ${domi.provincia}</td>
                <td><button onclick=updatePaciente(${paciente.id}) class="btn btn-warning">Modificar</button></td>
                <td><button onclick=deletePaciente(${paciente.id}) class="btn btn-danger">Eliminar</button></td>
             </tr>
            `
            plantilla+=row
        })

        tableBody.innerHTML = plantilla


      });

})


let editId = document.getElementById("editId");
const updatePaciente = (id) => {
  //  for (let i of td) {
   //   i.classList.remove("edit-dom");
  //  }

   // document.getElementById(`idDom${id}`).classList.add("edit-dom");

    formEditPaciente.classList.remove("d-none");

    editId.value = id;
}

const deletePaciente = (id) => {
   const url = `/paciente/${id}`;
         const settings = {
           method: 'DELETE',
           headers: {
              'accept': 'application/json',
              'Content-Type': 'application/json',
             }
         }

         fetch(url,settings)
         .then(response => console.log(response))
   alert("Paciente borrado")
   location.reload()
}

let formAddPaciente = document.getElementById("formAddPaciente");

formAddPaciente.addEventListener("submit", (event) => {
  event.preventDefault();

  const formData = {
    cedula: document.querySelector("#cedula").value,
    nombre: document.querySelector("#nombre").value,
    apellido: document.querySelector("#apellido").value,
    fechaIngreso: document.querySelector("#fechaIngreso").value,
    email: document.querySelector("#email").value,
    domicilio: {
                       "calle": document.querySelector("#calle").value,
                       "numero": document.querySelector("#numCalle").value,
                       "localidad": document.querySelector("#localidad").value,
                       "provincia": document.querySelector("#provincia").value
                }
  };

  const url = "/paciente";
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
      alert("Paciente agregado correctamente")
      location.reload();
    })
    .catch((error) => {
      console.log(error);
    });
});

let formEditPaciente = document.getElementById("formEditPaciente");

formEditPaciente.addEventListener("submit", (event) => {
  event.preventDefault();
  let editId = document.querySelector('#editId').value;

  const formData = {
      id: document.querySelector('#editId').value,
      cedula: document.querySelector("#editCedula").value,
      nombre: document.querySelector("#editNombre").value,
      apellido: document.querySelector("#editApellido").value,
      fechaIngreso: document.querySelector("#editFechaIngreso").value,
      email: document.querySelector("#editEmail").value,
      domicilio: {
                         "calle": document.querySelector("#editCalle").value,
                         "numero": document.querySelector("#editNumCalle").value,
                         "localidad": document.querySelector("#editLocalidad").value,
                         "provincia": document.querySelector("#editProvincia").value
                  }
  };


  const url = '/paciente/'+editId;
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
          alert("Paciente modificado correctamente")
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