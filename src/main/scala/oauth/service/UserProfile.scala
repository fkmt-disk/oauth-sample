package oauth.service

object UserProfile {

  def apply(email: String): UserProfile = {
    // emailを元にDBとか検索してユーザ情報を生成
    // または新規登録する処理が入るはず
    apply(email.split("@").head, email)
  }

}

case class UserProfile(name: String, email: String)
