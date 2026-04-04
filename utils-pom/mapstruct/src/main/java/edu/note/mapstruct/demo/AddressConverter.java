package edu.note.mapstruct.demo;

import org.mapstruct.Mapper;

import edu.note.mapstruct.demo.entity.Address;

/**
 * @author jackylee
 * @date 2026-03-18 21:01
 */
@Mapper
public interface AddressConverter {

    public String addressToString(Address address);

}
