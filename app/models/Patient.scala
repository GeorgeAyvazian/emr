package models

import javax.persistence.{Column, Entity, GeneratedValue, Id, Table}

import play.api.libs.json.JsValue
import play.db.ebean.Model

import scala.annotation.meta.field

@Entity
@Table(name = "patients")
case class Patient(
                    @(Column@field)(name = "firstName")
                    firstName: String = "",
                    @(Column@field)(name = "lastName")
                    lastName: String = "",
                    @(Column@field)(name = "dob")
                    dob: String = "",
                    @(Id@field) @(GeneratedValue@field)
                    id: Long = 0L)
  extends Model {

  def this() = this("", "", "")
}

object Patient {
  def create(jsPatient: JsValue): Long = {
    val firstName = (jsPatient \ "firstName").as[String]
    val lastName = (jsPatient \ "lastName").as[String]
    val dob = (jsPatient \ "dob").as[String]
    val newPatient: Patient = Patient apply(firstName, lastName, dob)
    newPatient save()
    newPatient.id
  }


  import play.api.libs.json.{JsArray, Json}

  implicit val writer = Json.writes[Patient]

  import play.db.ebean.Model.Finder

  private val finder: (Finder[Long, Patient]) = new Finder(classOf[Long], classOf[Patient])

  def findAll: JsArray = {
    import scala.collection.JavaConversions.collectionAsScalaIterable
    Json arr collectionAsScalaIterable(finder all).toList
  }

  def delete(id: Long) = {
    finder setId id
    (finder byId id).delete()
  }
}