package lectures.week2oop.ObjectsOOP

object Accounts extends App {
  val branch = new Branch()
  val account = branch.openAccount("business")
}

class PersonalAccount

class BusinessAccount


class Branch {
  def openAccount(accountType: String) = {
    Account(accountType)
  }
}

object Account {
  def apply(accountType: String) = {
    if (accountType == "business") new BusinessAccount
    else new PersonalAccount
  }
}


