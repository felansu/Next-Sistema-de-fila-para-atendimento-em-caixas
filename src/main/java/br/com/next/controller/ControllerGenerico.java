package br.com.next.controller;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.next.model.UtilBaseEntities;

public abstract class ControllerGenerico<T extends UtilBaseEntities<Long>> {

    protected abstract CrudRepository<T, Long> getService();

    @ResponseBody
    @RequestMapping(value = "/Inserir", method = RequestMethod.POST)
    public T inserir(@RequestBody T entidade) {
        return getService().save(entidade);
    }

    @ResponseBody
    @RequestMapping(value = "/Alterar", method = RequestMethod.PUT)
    public T alterar(@RequestBody T entidade) {
        return getService().save(entidade);
    }


    @ResponseBody
    @RequestMapping(value = "/Excluir", method = RequestMethod.DELETE)
    public void excluir(@RequestParam(value = "id") Long id) {
        getService().delete(id);
    }

    @ResponseBody
    @RequestMapping(value = "/Listar", method = RequestMethod.GET)
    public Iterable<T> listar() {
        return getService().findAll();
    }

    @ResponseBody
    @RequestMapping(value = "/Localizar", method = RequestMethod.GET)
    public T localizar(@RequestParam(value = "id") Long id) {
        return getService().findOne(id);
    }
}