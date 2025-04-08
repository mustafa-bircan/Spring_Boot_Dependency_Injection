#  Java Spring Boot Dependency Injection

### Proje Kurulumu

Projeyi öncelikle forklayın ve clone edin.
Daha sonra projeyi IntellijIDEA kullanarak açınız. README.md dosyasını dikkatli bir şekilde okuyarak istenenleri yapmaya çalışın.
Proje sayımız ilerledikçe proje yönetimimizi kolaylaştırmak adına projelerimizi belli klasör kalıplarında saklamak işimizi kolaylaştırmak adına iyi bir alışkanlıktır.
Örnek bir Lokasyon: Workintech/Sprint_1/Etud.

### Hedeflerimiz:

### Spring Boot Dependency Injection

 ### Görev 1
 * Maven dependency management sistemini kullanarak tüm dependencyleri install edin.
 * Uygulamanızı  ```8585``` portundan ayağa kaldırın.
 * Tüm endpointlerin önüne ```workintech``` gelmesi için ilgili ```application.properties``` içerisine ilgili düzenlemeyi yapın.
 * Spring devtools kullanarak uygulamanızın her değişim sonrasında kendisini restart etmesini sağlayınız.
 * Uygulamamızda ```main``` metodumuzun bulunduğu paket dışında iki adet daha paket tanımlayınız. ```model``` ve ```tax``` isminde olabilirler.
 * Uygulamamızda ```main``` metodumuzun bulunduğu sınıf dışında kalıcak şekilde ```rest``` isimli bir paket oluşturunuz.
 * ```rest``` paketi içerisinde ````DeveloperController```` isimli bir controller tanımlayınız.
 * ```model``` paketi içerisinde ````Developer```` isminde bir adet class oluşturunuz. ```id, name, salary ve experience``` isimli 4 adet değişken tanımlayınız.
 * experience değeri enum tipinde olmalı JUNIOR, MID ve SENIOR değerlerinden birini almalı.
 * ````Developer```` sınıfı içerisinde tüm ````instance variable```` değerlerini set eden bir adet constructor tanımlayınız.
 * ```Developer``` sınıfını kullanan(ilişkinin nasıl olması gerektiğini siz tanımlamalısınız.) 3 ayrı sınıf tanımlayınız. ````JuniorDeveloper````, ````MidDeveloper````, ````SeniorDeveloper````
 
 ### Görev 2
 * tax paketinin içerisine bir adet ````Taxable```` isimli interface tanımlayınız.
 * İçerisinde ````getSimpleTaxRate, getMiddleTaxRate, getUpperTaxRate```` isimli bir 3 adet metod tanımlayınız.
 * ````DeveloperTax```` bir adet sınıf yazınız. Taxable interface implement etmeli. ilgili metodları override etmeli.
 * ```getSimpleTaxRate``` 15d dönmeli.  ```getMiddleTaxRate``` 25d dönmeli. ```getUpperTaxRate``` 35d dönmeli.

 ### Görev 3
 * DeveloperController sınıfı içerisinde bir adet ```developers``` adında Map tanımlayın. ```Map<Integer, Developer>``` şeklinde değer almalı.
 *  ```@postConstruct``` annotation kullanarak developers map objesini tanımlayınız.
 * DeveloperController sınıfı içerisinde bir adet constructor tanımlanmalı Taxable interface ```Dependency Injection``` yöntemiyle çağırılmalı. DeveloperTax sınıfını çağırmalı.
 * Amacımız CRUD işlemlerini tanımlayan endpointler yazmak. 
 * [GET]/workintech/developers => tüm developers mapinin value değerlerini ```List``` olarak döner.
 * [GET]/workintech/developers/{id} => ilgili id deki developer mapte varsa value değerini döner.
 * [POST]/workintech/developers => ```id, name, salary ve experience``` değerlerini alır, experience tipine bakarak uygun developer objesini oluşturup developers mapine ekler. JuniorDeveloper için salary bilgisinden salary*getSimpleTaxRate() değerini düşmelisiniz. Aynı şekilde MidDeveloper için salary*getMiddleTaxRate(), SeniorDeveloper için  salary*getUpperTaxRate() değerlerini salary bilgisinden düşmelisiniz.
 * [PUT]/workintech/developers/{id} => İlgili id deki map değerini ```Request Body``` içerisinden aldığı değer ile günceller.
 * [DELETE]/workintech/developers/{id} => İlgili id değerini mapten siler.
 * Tüm endpointlerin dönüş değerleri JSON formatında olmalı.

 ### Görev 4
 * Spring Actuators endpointlerini kullanarak /mappings, /health ile uygulamanızın durumunu kontrol edin
 * /info nun çalışabilmesi için application.properties kısmına ```name```, ```description```, ```version``` kısımlarını tanımlayınız.
