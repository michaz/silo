package com.pb.sawdust.data.census.pums;

/**
 * The {@code PumaDataType} enum identifies a type of PUMA data.
 *
 * @author crf
 *         Started 1/27/12 4:19 PM
 */
public enum PumaDataType {
    /**
     * The {@code PumaDataType} for household data.
     */
    HOUSEHOLD(PumaDataField.PumaDataHouseholdField.class),
    /**
     * The {@code PumaDataType} for person data.
     */
    PERSON(PumaDataField.PumaDataHouseholdField.class);

    private final Class<? extends PumaDataField> pumaDataFieldInterface;

    private PumaDataType(Class<? extends PumaDataField> pumaDataFieldInterface) {
        this.pumaDataFieldInterface = pumaDataFieldInterface;
    }

    /**
     * Get the PUMA data field (interface) class that a PUMA data field of this type will (must) implement.
     *
     * @return the PUMA data field interface associated with this data type.
     */
    public Class<? extends PumaDataField> getPumaDataFieldInterface() {
        return pumaDataFieldInterface;
    }

    /**
     * Get the PUMA data type that a specified contains.
     *
     * @param field
     *        The field in question.
     *
     * @return the type of PUMA data that {@code field} contains.
     */
    public static PumaDataType getFieldType(PumaDataField field) {
        for (PumaDataType type : values())
            if (type.getPumaDataFieldInterface().isInstance(field))
                return type;
        throw new IllegalArgumentException("Unknown puma data type for field: " + field);
    }
}