package controllers

import models.Patient
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

object Patients extends Controller {

  /*def show(pin: Long) = Action { implicit request =>
    Patient.findByPin(pin).map { patient =>
      Ok(views.html.patients.details(patient))
    }.getOrElse(NotFound)
  }*/

  def all() = Action {
    Ok(Patient.findAll)
  }

  def create() = Action(parse.json) { request =>
    Ok(Json.toJson(Patient.create(request.body)))
  }

  /*def save = Action { implicit request =>
    val newPatientForm = patientForm.bindFromRequest()

    newPatientForm.fold(
    { form =>
      Redirect(routes.Patients.newPatient()).
        flashing(Flash(form.data) + ("error" -> Messages("validation.errors")))
    }, { newPatient =>
      Patient.add(newPatient)
      Redirect(routes.Patients.show(newPatient.id)).
        flashing("success" -> Messages("patients.new.success", newPatient.id))
    })
  }*/

  /*def newPatient = Action { implicit request =>
    val form = if (flash.get("error").isDefined) patientForm.bind(flash.data) else patientForm
    Ok(views.html.patients.editPatient(form))
  }*/
  def delete(id: String) = Action(parse.empty) { request =>
    val l: Long = id.toLong
    Patient.delete(l)
    Ok(Json.toJson(l))
  }
}