package edu.metrostate.refactors;

import java.time.LocalDate;

public abstract class FrozenFood extends PerishableFood {

    public FrozenFood (int id, String name, long shelfLifeDays, LocalDate expirationDate) {
        super(id, name, shelfLifeDays, expirationDate);
    }
}