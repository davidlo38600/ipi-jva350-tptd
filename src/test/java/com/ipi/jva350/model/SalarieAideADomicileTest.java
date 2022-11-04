package com.ipi.jva350.model;


import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
// import org.junit.jupiter.api.Assertions;
// import org.testing.annotations.Test;

import java.time.LocalDate;

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
}
