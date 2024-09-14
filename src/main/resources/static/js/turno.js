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
        const selectPaciente = document.getElementById("paciente");

        let plantilla1 = ''

        data.forEach((paciente) => {
            let option = `<option value=${paciente.id}>${paciente.nombre} ${paciente.apellido}</option>`
            plantilla1+=option
        })

        selectPaciente.innerHTML = plantilla1


      });

      const url1 = '/odontologo';
      const settings1 = {
        method: 'GET',
        mode: 'no-cors',
        headers: {
            'accept': 'application/json',
           'Content-Type': 'application/json',
          }
      }

      fetch(url1,settings1)
      .then(response => response.json())
      .then(data => {
      console.log(data)
        const selectOdontologo = document.getElementById("odontologo");

        let plantilla2 = ''

        data.forEach((odontologo) => {
            let option = `<option value=${odontologo.id}>${odontologo.nombre} ${odontologo.apellido}</option>`
            plantilla2+=option
        })

        selectOdontologo.innerHTML = plantilla2

      });

})


let formularioTurno = document.getElementById("formularioTurno");

formularioTurno.addEventListener("submit", (event) => {
  event.preventDefault();

  const formData = {
    fecha: document.querySelector("#fecha").value,
    odontologo: {id: document.querySelector("#odontologo").value},
    paciente: {id: document.querySelector("#paciente").value}

  };

  const url = "/turno";
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
      console.log(data)
      alert("Turno registrado correctamente con el id "+data.id)
      location.reload();
    })
    .catch((error) => {
      console.log(error);
    });
});

const buscadorTurnos = document.getElementById("buscadorTurnos");

buscadorTurnos.addEventListener('submit', (e) => {
    e.preventDefault();
    const idTurno = document.getElementById("idTurno").value;
      const url = '/turno/'+idTurno;
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
       alert(`El turno ID ${data.id} tiene fecha para el ${data.fecha} con el paciente ${data.paciente.nombre} ${data.paciente.apellido} y el odontólogo ${data.odontologo.nombre} ${data.odontologo.apellido}`)
      });
})