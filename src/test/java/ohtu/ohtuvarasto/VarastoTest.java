package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void varastoonEiVoiLisätäEnempääKuinMahtuu() {
        varasto.lisaaVarastoon(20);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastostaVoiOttaaVainSenVerranKuinSielläOn() {
        varasto.lisaaVarastoon(8);        
        assertEquals(8, varasto.otaVarastosta(20), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivisenTilavuudenAsettaminenLuoOlemattomanVarastonEnsimmäiselläKonstruktorilla() {
        Varasto v = new Varasto(-2);
        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivisenTilavuudenAsettaminenLuoOlemattomanVarastonToisellaKonstruktorilla() {
        Varasto v = new Varasto(-2, 1);
        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktorissaVoiLisätäVarastoonSaldoa() {
        Varasto v = new Varasto(20, 10);
        assertEquals(10, v.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktorissaEiVoiLisätäNegatiivistaSaldoa() {
        Varasto v = new Varasto(20, -1);
        assertEquals(0, v.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktorissaEiVoiLisätäLiikaaSaldoa() {
        Varasto v = new Varasto(20, 21);
        assertEquals(20, v.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastoonEiVoiLisätäNegatiivistaSaldoa() {
        varasto.lisaaVarastoon(2);
        varasto.lisaaVarastoon(-1);
        assertEquals(2, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastostaEiVoiOttaaNegatiivistaSaldoa() {
        varasto.lisaaVarastoon(2);
        assertEquals(0, varasto.otaVarastosta(-1), vertailuTarkkuus);
    }
    
    @Test
    public void merkkijonoesitysOnOikeanlainen() {
        varasto.lisaaVarastoon(2);
        assertEquals("saldo = 2.0, vielä tilaa 8.0", varasto.toString());
    }

}