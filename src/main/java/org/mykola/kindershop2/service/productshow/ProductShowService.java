package org.mykola.kindershop2.service.productshow;

import org.mykola.kindershop2.entity.product.ProductShow;
import org.mykola.kindershop2.repository.product.ProductShowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductShowService {
	private final ProductShowRepo psr;
	
	@Autowired
	public ProductShowService(ProductShowRepo psr) {
		this.psr = psr;
	}
	
	public ProductShow showOne(Long id) {
		return psr.findById(id).get();
	}
}
