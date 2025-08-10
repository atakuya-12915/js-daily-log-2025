// JpaRepository<Role, Integer> により roles テーブルへの基本的なCRUD操作を自動生成
public interface RoleRepository extends JpaRepository<Role, Integer> {
    // findByName()：[Roleエンティティ]から[roles.name列に一致]する[ロール]を取得する専用メソッド
    public Role findByName(String name);  // name(ロール名) で Roleエンティティを検索して、Role型で取得する
}

// 用途：主にユーザ作成時や認証処理時に利用される。
    // User の role フィールドに値を設定する役割。