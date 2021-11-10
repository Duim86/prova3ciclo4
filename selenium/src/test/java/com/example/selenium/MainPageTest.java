package com.prova.prova34;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;

import java.util.Objects;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class MainPageTest {
  @BeforeAll
  static void setUpAll() {
    Configuration.browserSize = "1280x800";
    SelenideLogger.addListener("allure", new AllureSelenide());
  }

  @BeforeEach
  void setUp() {
    open("http://localhost:3000");
  }

  @Test
  void deveCadastrarUmNovoContatoQuandoDadosForemInformadosCorretamente() {
    deveCadastrarUmContato("Arrascaeta", "41-5555555", "arrascaeta@flamengo.com");

    Alert alert = Selenide.switchTo().alert();
    String actualMessage = alert.getText();
    String expectedMessage =  "Cadastro realizado com sucesso!";

    Assertions.assertEquals(expectedMessage, actualMessage);

  }

  @Test
  void deveLancarErroAoInformarEmailRepetido() {

    deveCadastrarUmContato("Michael", "41-55555555", "michael@flamengo.com");

    deveCadastrarUmContato("Michael", "41-55554545", "michael@flamengo.com");

    Alert alert = Selenide.switchTo().alert();
    String actualMessage = alert.getText();
    String expectedMessage =  "";

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
