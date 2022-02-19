package com.example.application.forms;

// @author Henrique Andrew da Silva
import com.example.application.ConfirmationDialog;
import com.example.application.model.Produto;
import com.example.application.model.ProdutoClassificacao;
import com.example.application.model.ProdutoRepository;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class ProdutoForm extends FormLayout {

    private TextArea descricao = new TextArea("Descrição do produto");
    private ComboBox<ProdutoClassificacao> classif = new ComboBox<>("classificação do produto");
    private TextField unid_Medida = new TextField("Unidade de medida");
    NumberField valor_unit = new NumberField("Valor");
    Checkbox cadAtivo = new Checkbox();

    Button gravar = new Button("Gravar");
    Button excluir = new Button("Excluir");

    private MainView mainView;
    private ConfirmationDialog confirmDialog = new ConfirmationDialog();

    private Binder<Produto> binder = new Binder<>(Produto.class);
    private ProdutoRepository repository;

    public ProdutoForm(MainView mainView, ProdutoRepository repository) {
        this.mainView = mainView;
        this.repository = repository;
        binder.bindInstanceFields(this);
        classif.setItems(ProdutoClassificacao.values());

        HorizontalLayout btns = new HorizontalLayout(gravar, excluir);
        gravar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        gravar.addClickListener(event -> gravar());
        excluir.addClickListener(event -> excluir());
        
        add(descricao, classif, unid_Medida, valor_unit, btns);

    }
    
    public void setProduto(Produto produto) {
        binder.setBean(produto);

        if (produto == null) {
            setVisible(false);
        } else {
            setVisible(true);
            if (binder.getBean().isPersisted()) {
                excluir.setVisible(true);
            } else {
                excluir.setVisible(false);
            }
            descricao.focus();
        }
    }
    
    private void gravar() {
        Produto produto = binder.getBean();
        repository.save(produto);
        mainView.atualizarLista();
        setProduto(null);
    }
    
    private void excluir() {
        Produto produto = binder.getBean();
        confirmDialog.setTitle("EXCLUIR CADASTRO");
        confirmDialog.setQuestion("Deseja realmente excluir o produto '"+ produto.toString() + "'?");
        confirmDialog.open();
        confirmDialog.addConfirmationListener(evt -> {
            repository.delete(produto);
            mainView.atualizarLista();
            setProduto(null);
        });
    }   
    
}
