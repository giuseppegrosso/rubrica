package it.plansoft.rubrica.controller;/* ggrosso created on 04/02/2021 inside the package - it.plansoft.rubrica.controller */

import it.plansoft.rubrica.service.BaseCrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * controller astratto con service e loggin pronti.
 * @param <SERVICE>
 */
public abstract class Abstract2Controller<SERVICE extends BaseCrudService> {

    protected final static Logger log = LoggerFactory.getLogger(Abstract2Controller.class);

    protected SERVICE service;
}
