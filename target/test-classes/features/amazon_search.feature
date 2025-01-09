Feature: Amazon Arama Fonksiyonu

  Scenario: Kullanici Amazon'da urun arayabilmeli
    Given Kullanici Amazon ana sayfasina gider
    When Kullanici arama kutusuna "laptop" yazar
    And Kullanici arama butonuna tiklar
    Then Kullanici arama sonuclarini görmeli
    And Sonuclarda "laptop" kelimesi bulunmali 