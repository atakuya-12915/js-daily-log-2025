// JpaRepository<User, Integer> により users テーブルへの基本的なCRUD操作を自動生成
public interface UserRepository extends JpaRepository<User, Integer> {
  // findByEmail()：[Userエンティティ]から[users.email列に一致]する[メールアドレス]を取得する専用メソッド
   public User findByEmail(String email); // メールアドレスでUserエンティティを検索
}

// 用途：主にログイン認証や会員情報表示に利用される。
    // User の email フィールドに値を設定する役割。