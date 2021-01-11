package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassGeneratorTest {

    private PassGenerator passGen;

    public PassGeneratorTest(){
        this.passGen = new PassGenerator();
    }

    @Test
    void generatePassword() {
        String password = passGen.generatePassword();
        assertEquals(7,password.length());
    }
}