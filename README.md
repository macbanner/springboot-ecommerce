# 📦 E-Commerce Uygulaması – Proje Dokümantasyonu

## 1. Giriş 

Bu proje, basit bir e-ticaret sisteminin backend tarafını oluşturmak amacıyla geliştirilmiştir. Kullanıcılar ürünleri görüntüleyebilir, sepete ekleyebilir ve sipariş oluşturabilir. Spring Boot tabanlı bu yapı, örnek bir uygulama olarak katmanlı mimari ile geliştirilmiştir.

---

## 2. Kullanılan Teknolojiler 🛠️

| Teknoloji        | Açıklama                                       |
|------------------|------------------------------------------------|
| Java 17          | Ana geliştirme dili.                          |
| Spring Boot      | Ana framework.                                |
| Maven            | Bağımlılık ve proje yönetimi.                 |
| Spring Data JPA  | Veritabanı işlemleri için.                    |
| Lombok           | Getter/Setter kodlarının kolay implementasyonu|
| H2 Database      | Gömülü veritabanı                             |

---

## 3. Projenin Amacı 

Spring mimarisiyle temel bir e-ticaret uygulaması oluşturmak.

---

## 4. Proje Yapısı 

Proje, aşağıdaki temel katmanlardan oluşmaktadır:

*   **`controller/`**: HTTP isteklerini karşılayan sınıflar.
*   **`service/`**: İş mantığının yazıldığı katman.
*   **`repository/`**: Veritabanı ile etkileşim sağlar (JPA).
*   **`entity/`**: Tabloları temsil eden sınıflar.
*   **`resources/`**: Konfigürasyon ve statik kaynaklar.
*   **`test/`**: Test sınıfları.

---

## 5. Veritabanı Yapısı 

Temel veritabanı varlıkları şunlardır:

*   **User**: Kullanıcı bilgileri.
*   **Product**: Ürün listesi.
*   **Cart**: Sepet yapısı.
*   **Order**: Sipariş kayıtları.
