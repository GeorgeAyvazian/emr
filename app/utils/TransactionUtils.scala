package utils

import java.util

object TransactionUtils {

  def getResults[T](hql: String): List[T] = {
    var results: util.List[_] = ???
    import play.db.jpa.JPA
    import play.libs.F.Callback0
    JPA.withTransaction(new Callback0 {
      override def invoke(): Unit = {
        results = JPA.em().createQuery(hql).getResultList
      }
    })
    results map {
      _.asInstanceOf[T]
    }
  }

  import scala.collection.JavaConversions.collectionAsScalaIterable

  implicit def convertList[A](value: util.List[A]): List[A] = collectionAsScalaIterable(value).toList
}