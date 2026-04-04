package edu.note.mapstruct.demo.entity;

import edu.note.mapstruct.demo.enums.AddressType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jackylee
 * @date 2026-03-18 20:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String country;

    private String city;

    private AddressType addressType;

}
