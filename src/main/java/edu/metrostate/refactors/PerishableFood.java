package edu.metrostate.refactors;

import java.time.LocalDate;

public abstract class PerishableFood extends Food {

    private final long shelfLifeDays;
    private final LocalDate expirationDate;

    public PerishableFood (int id, String name, long shelfLifeDays, LocalDate expirationDate) {
        super (id, name);
        this.expirationDate = expirationDate;
        this.shelfLifeDays = shelfLifeDays;
    }

    public long getShelfLifeDays() {
        return shelfLifeDays;
    }

    public LocalDate getExpirationDate () {
        return expirationDate;
    }

    public boolean hasExpired () {
        return expirationDate.isAfter(LocalDate.now());
    }

    @Override
    public boolean equals (Object object) {
        if (object == this) return true;
        if (object == null) return false;
        if (object instanceof PerishableFood perishableFood)
            return super.equals(perishableFood) && shelfLifeDays == perishableFood.getShelfLifeDays();
        return false;
    }
}