package com.awie.awiepetclinic.services.map;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.awie.awiepetclinic.model.Owner;
import com.awie.awiepetclinic.model.Pet;
import com.awie.awiepetclinic.services.OwnerService;
import com.awie.awiepetclinic.services.PetService;
import com.awie.awiepetclinic.services.PetTypeService;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService{

	private final PetTypeService petTypeService;
	private final PetService petService;
	
	public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
		this.petTypeService = petTypeService;
		this.petService = petService;
	}

	@Override
	public Set<Owner> findAll() {
		return super.findAll();
	}
	
	@Override
	public Owner findById(Long id) {
		return super.findById(id);
	}

	@Override
	public Owner save(Owner owner) {
		
		if(owner != null) {
			if(owner.getPets() != null) {
				owner.getPets().forEach(pet -> {
					if(pet.getPetType() != null) {	
						if(pet.getPetType().getId() == null) {
							pet.setPetType(petTypeService.save(pet.getPetType()));
						}
					}else {
						throw new RuntimeException("Pet Type is Required.");
					}
					
					if(pet.getId() == null) {
						Pet savedPet = petService.save(pet);
						pet.setId(savedPet.getId());
					}
				});
			}
			
			return super.save(owner);
		}else {
			return null;
		}
	
	}
	
	@Override
	public void delete(Owner object) {
		super.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

	@Override
	public Owner findByLasName(String lastName) {
		return null;
	}
}
