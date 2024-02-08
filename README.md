## İçerik

Öncelikli olarak herkese merhaba.Bu projemde Jetpack Compose ile bir ülkeler uygulaması geliştirmeye çalıştımUygulama içerisinde 6 bölge , 16 alt bölge mevcuttur.
Uygulama içerisinde ülkeleri listeleyebileceğimiz,bölgelere göre ülkeleri listeleyeceğimiz,bu ülkelerin
başkent,isim ve yerel bayraklarına göre ve bölgelere göre öğrenmek için yapacağımız bir Quiz kısımı ekledim.Yine ülkeleri detay sayfasında favoriye alıp daha sonra inceleyebileceğimiz bir kısım da mevcut.Ülkelerin para birimlerinin
olduğu bir sayfada mevcut.Ayrıca ülkelerin isimlerine göre aratabileceğimizbir arama kısımıda eklemeyi ihmal etmedim :) . All Country kısımında ülkelerin hepsini doğrudan listelemeden kendi oluşturduğum paging 
algoritmasını kullandım.Bu algoritma kaydırdıkça 20 şer adet yüklenmesini sağlıyor.Yani ilk 20 adet ülke çekildikten sonra ki kaydırmada 40 adet çekiliyor.
Fakat daha önce elimde 20 adet olduğu için ben hep son 20 gelen ülkeyi liste içerisine alarak işlem yaptım.
Böylelikle tek seferde tüm verilerin çekilmesini engelledim ve telefondaki kasma,donma,aniden belleğin dolması gibi durumların önüne geçmeye çalıştım.
Böylelikle tam fonksiyonel bir uygulama geliştirmeye çalıştım.Uygulamayı geliştirirken Stateless,Statefull,State Hoisting gibi kavramları özümseyerek kullanmaya çalıştım.
Clean olması için gerekli methodları ve özellikleri de olabildiğince uygulamaya çalıştım.Gereksiz kod tekrarlarından kurtulduğum fonksiyon,class,composable lar mevcut.
Kullanıcıya quizde 3 hak tanınıyor.3 yanlış sonunda butona basıp tekrar 3 hak alabiliyor.


## Kullanılan Teknolojiler


-Flow



-Retrofit



-MVVM



-State




-Hilt




-Room




-Timber





-Coroutines




-Navigations,Arguments




## Video



https://github.com/Cntrk01/CountriesAppCompose/assets/98031686/6dab9588-4383-4eaa-af68-11761c922956



https://github.com/Cntrk01/CountriesAppCompose/assets/98031686/3e2387c9-de8a-440c-80e7-624082ad494b



https://github.com/Cntrk01/CountriesAppCompose/assets/98031686/dd657700-94af-4a0e-a3ce-1af02e043b4e



## Çıktılar


![WhatsApp Image 2024-02-08 at 23 31 47 (2)](https://github.com/Cntrk01/CountriesAppCompose/assets/98031686/a8e23966-8323-4a6a-88d5-01bb2c8f049c)![WhatsApp Image 2024-02-08 at 23 31 47 (1)](https://github.com/Cntrk01/CountriesAppCompose/assets/98031686/799db3de-4ee2-46d9-adde-833ca271ce5f)
![WhatsApp Image 2024-02-08 at 23 31 47](https://github.com/Cntrk01/CountriesAppCompose/assets/98031686/5269d2ed-55d0-4800-987a-19aba5afc9b7)![WhatsApp Image 2024-02-08 at 23 31 46 (2)](https://github.com/Cntrk01/CountriesAppCompose/assets/98031686/ac1c10e1-2b0f-4840-afd0-17b8d658dba1)
![WhatsApp Image 2024-02-08 at 23 31 46 (1)](https://github.com/Cntrk01/CountriesAppCompose/assets/98031686/b5eee246-a52e-4d7f-a636-0fe55664fe4d)![WhatsApp Image 2024-02-08 at 23 31 46](https://github.com/Cntrk01/CountriesAppCompose/assets/98031686/dd356b53-984a-493f-b0b6-c422acb100d0)
![WhatsApp Image 2024-02-08 at 23 31 47 (5)](https://github.com/Cntrk01/CountriesAppCompose/assets/98031686/6a8b9e03-adaf-4a93-8ab8-3aa61d265ad7)![WhatsApp Image 2024-02-08 at 23 31 47 (4)](https://github.com/Cntrk01/CountriesAppCompose/assets/98031686/26f1f74f-e6a4-4129-9c03-8df5691f75be)
![WhatsApp Image 2024-02-08 at 23 31 47 (3)](https://github.com/Cntrk01/CountriesAppCompose/assets/98031686/acfc1792-e151-4ccd-8d1d-b0caff543fe3)
