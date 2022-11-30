package com.ipi.jva350.model;


import com.ipi.jva350.exception.SalarieException;
import jdk.vm.ci.meta.Local;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.provider.CsvSource;
// import org.junit.jupiter.api.Assertions;
// import org.testing.annotations.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class SalarieAideADomicileTest {
    // TEST VIDE :
    @Test
    void testALegalementDroitADesCongesPayesVide() {
        // Given :
        SalarieAideADomicile aide = new SalarieAideADomicile();
        // When :
        boolean res = aide.aLegalementDroitADesCongesPayes();
        // Then :
        // Assertion assertj : Assertions.assertThat(res).isFalse();
        Assertions.assertThat(res).isFalse();
    }
    // TEST DROIT CONGES PAYER :
    @Test
    void testALegalementDroitADesCongesPayesTrue() {
        // Given :
        SalarieAideADomicile aide = new SalarieAideADomicile("Jeanne", LocalDate.now(), LocalDate.now(),0,0,55,5,0);
        // When :
        boolean res = aide.aLegalementDroitADesCongesPayes();
        // Then Assertion assertj :  :
         Assertions.assertThat(res).isTrue();

    }

    // TEST LIMITE ENTRE LES DEUX :
    @Test
    void testALegalementDroitADesCongesPayesLimiteCasAuLimite() {
        //ASSERT 9 JOUR :
        // Given :
        SalarieAideADomicile aide = new SalarieAideADomicile("Jeanne", LocalDate.now(), LocalDate.now(),0,0,9,5,0);
        // When :
        boolean res = aide.aLegalementDroitADesCongesPayes();
        // Then :
        Assertions.assertThat(res).isFalse();


        // ASSERT 10 JOUR :
        aide.setJoursTravaillesAnneeNMoins1(10);
        // When :
        boolean res1 = aide.aLegalementDroitADesCongesPayes();
        // Then :
        Assertions.assertThat(res1).isTrue();
    }

    // Maxime ANCELIN
    @Test
    public final void estDansPlage(){
        boolean estDansPlage = entreprise.estDansPlage(LocalDate.of(2021, 7, 1), LocalDate.of(2022, 8, 6));
        assertTrue(estDansPlage);
    }

    // Maxime ANCELIN
    @Test
    public final void estHorsPlageAvant(){
        boolean estDansPlage = entreprise.estDansPlage(LocalDate.of(2021, 7, 1), LocalDate.of(2022, 8, 6));
        assertFalse(estDansPlage);
    }

    // Louis DAVID
    @Test
    public final void estHorsPlageApres(){
        boolean estDansPlage = entreprise.estDansPlage(LocalDate.of(2022, 7, 1), LocalDate.of(2022, 8, 6));
        assertTrue(estDansPlage);
    }

    // Louis DAVID
    @Test
    void testEstHorsPlageNull()
    {
        Entreprise entreprise = new Entreprise();

        boolean estDansPlage = entreprise.estDansPlage(null, LocalDate.of(2021, 7, 10), LocalDate.of(2022, 8, 10));
        assertFalse(estDansPlage);
    }

    // Maxime ANCELIN
    @Test
    public final void clotureMois() throws SalarieException {
        SalarieAideADomicile aide = new SalarieAideADomicile("Maxime", LocalDate.of(2021, 7, 1));

        salaireAideADomicileService.clotureMois(aide, 20.0);
        assertNotEquals(2, aide.getJoursTravailleAnneeN());
    }

    // Maxime ANCELIN & Louis DAVID
    @ParameterizedTest(name = "Date : {0} | Férié ? : {1}")
    @CsvSource({
            "'2012-04-08', false",
            "'2012-06-08', false",
            "'2020-04-08', false",
            "'2013-04-08', false",
            "'2012-01-01', true"
    })

    // Louis DAVID
    public final void estJourFerie(String date, boolean expectedJourFerierBool) {
        Entreprise entreprise = new Entreprise();

        LocalDate d = LocalDate.parse(date);

        boolean jourFerier = entreprise.estJourFerie(d);
        assertEquals(expectedJourFerierBool, jourFerier);
    }
}
