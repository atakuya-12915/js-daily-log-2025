@Entity // JPAのエンティティクラスであることを示す
@Table(name = "roles") // 対応するテーブル名を指定（roles）
@Data // Lombokがgetter/setter/toString等を自動生成
public class Role {
   @Id // 主キーを示す
   @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT に対応
   @Column(name = "id") // roles.id に対応
   private Integer id;

   @Column(name = "name") // roles.name に対応
   private String name; // ロール名（例: ROLE_USER, ROLE_ADMIN）⭐️RoleRepository のfindByName()メソッドで取得
}
