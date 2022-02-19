package com.example.application.views.main;

import com.example.application.forms.ProdutoForm;
import com.example.application.model.Produto;
import com.example.application.model.ProdutoRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.springframework.beans.factory.annotation.Autowired;

@Route
public class MainView extends VerticalLayout {

    private final ProdutoRepository repository;
    private Grid<Produto> grid = new Grid<>(Produto.class);
    private TextField filtro = new TextField();
    Button novoProduto = new Button("Novo produto");

    private ProdutoForm form;

    public MainView(@Autowired ProdutoRepository repository) {
        this.repository = repository;

        form = new ProdutoForm(this, repository);
        form.setProduto(null);

        grid.setColumns("descricao", "classif", "unid_medida", "valor_unit");
        grid.setSizeFull();
        grid.asSingleSelect().addValueChangeListener(e -> selecionarProduto());
        atualizarLista();

        filtro.setPlaceholder("Filtrar por descrição");
        filtro.setClearButtonVisible(true);
        filtro.setValueChangeMode(ValueChangeMode.EAGER);
        filtro.addValueChangeListener(e -> atualizarLista());

        novoProduto.addClickListener(e -> adicionarProduto());

        HorizontalLayout mainContent = new HorizontalLayout(grid, form);

        mainContent.setSizeFull();

        add(new HorizontalLayout(filtro, novoProduto), mainContent);
        setSizeFull();

    }
    
    public void atualizarLista() {
        if (filtro.getValue().isEmpty()) {
            grid.setItems(repository.findAll());
        } else {
            grid.setItems(repository.findByDescricao(filtro.getValue()));
        }
    }
    
    public void selecionarProduto() {
        form.setProduto(grid.asSingleSelect().getValue());
    }

    private void adicionarProduto() {
        grid.asSingleSelect().clear();
        form.setProduto(new Produto());
    }

}
