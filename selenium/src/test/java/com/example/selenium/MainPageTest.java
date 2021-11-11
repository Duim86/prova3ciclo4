package com.prova.prova34;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;

import java.util.Objects;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class MainPageTest {

  @BeforeEach
  void setUp() {
    Configuration.browserSize = "1280x800";
    SelenideLogger.addListener("allure", new AllureSelenide());
    open("http://localhost:3000");
  }

  @AfterEach
  void teardown() {
    Selenide.closeWebDriver();
  }

  @Test
  void deveCadastrarUmNovoContatoQuandoDadosForemInformadosCorretamente() {
    deveCadastrarUmContato("Arrascaeta", "41-4545454545", "arrascaeta@flamengo.com");

    Alert alert = Selenide.switchTo().alert();
    String actualMessage = alert.getText();
    String expectedMessage =  "Cadastro realizado com sucesso!";

    Assertions.assertEquals(expectedMessage, actualMessage);

  }

  @Test
  void deveLancarErroAoInformarEmailInvalido() {

    deveCadastrarUmContato("Michael", "41-555555555", "michaelflamengo.com");


    Alert alert = Selenide.switchTo().alert();
    String actualMessage = alert.getText();
    String expectedMessage =  "Email inválido";

    Assertions.assertEquals(expectedMessage, actualMessage);
  }

  @Test
  void deveLancarErroAoInformarTelefoneInvalido() {

    deveCadastrarUmContato("Bruno Henrique", "41-aa55555555", "brunohenrique@flamengo.com");


    Alert alert = Selenide.switchTo().alert();
    String actualMessage = alert.getText();
    String expectedMessage =  "Telefone inválido";

    Assertions.assertEquals(expectedMessage, actualMessage);
  }

  @Test
  void deveLancarErroAoInformarTelefoneRepetido() {

    deveCadastrarUmContato("Filipe Luis", "41-44444444", "filipe.luis@flamengo.com");

    deveCadastrarUmContato("Filipe Luis", "41-44444444", "filipeluis@flamengo.com");


    Alert alert = Selenide.switchTo().alert();
    String actualMessage = alert.getText();
    String expectedMessage =  "Telefone já cadastrado";

    Assertions.assertEquals(expectedMessage, actualMessage);
  }

  @Test
  void deveLancarErroAoInformarEmailRepetido() {

    deveCadastrarUmContato("Filipe Luis", "41-88888888", "filipeluis@flamengo.com");

    deveCadastrarUmContato("Filipe Luis", "41-99999999", "filipeluis@flamengo.com");


    Alert alert = Selenide.switchTo().alert();
    String actualMessage = alert.getText();
    String expectedMessage =  "Email já cadastrado";

    Assertions.assertEquals(expectedMessage, actualMessage);
  }

  @Test
  void deveApagarUmUsuarioComSucesso() {
    deveCadastrarUmContato("Gabigol", "41-55555555", "gabriel@flamengo.com");

    var id = $(byText("Gabigol")).getAttribute("id");
    var str = Objects.requireNonNull(id).replaceAll("\\D+","");
    $("[id=remover-"+str+"]").click();

    Alert alert = Selenide.switchTo().alert();
    String actualMessage = alert.getText();
    String expectedMessage =  "Registro removido com sucesso!";

    Assertions.assertEquals(expectedMessage, actualMessage);
  }

  void deveCadastrarUmContato(String nome, String telefone, String email) {
    $("[id='btn-novo-contato']").click();
    $("[name='nome']").sendKeys(nome);
    $("[name='telefone']").sendKeys(telefone);
    $("[name='email']").sendKeys(email);
    $("[id='btn-cadastrar']").click();
  }
}
