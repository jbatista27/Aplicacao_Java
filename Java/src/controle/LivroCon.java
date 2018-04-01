package controle;
import modelo.Livro;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LivroCon{
    public boolean cadLivro(Livro livro){
        boolean resultado = false;
        try{
		Conexao conectar = new Conexao(); // Executar conexão com o banco.
		PreparedStatement ps = conectar.getCon().prepareStatement("INSERT INTO livros(titulo,paginas,valor) VALUES(?,?,?);"); // Prepara uma String para comando SQL Dinâmico.
		ps.setString(1,livro.getTitulo()); // Valor do atributo titulo do objeto livro
		ps.setInt(2,livro.getPaginas()); // Valor do atributo paginas do objeto livro
		ps.setFloat(3,livro.getValor()); // Valor do atributo valor do objeto livro.
		if(!ps.execute()){ // Executa a SQL * Retorna true quando acontecem erros.
			resultado = true;
		}
	}catch(SQLException e){
		System.out.println(e.getMessage());
	}
        return resultado;
    }
    public boolean editLivro(Livro livro){
        // Código para editar tupla
         try{
            Conexao conectar = new Conexao(); // Executar conexão com o banco.
            PreparedStatement ps = conectar.getCon().prepareStatement("UPDATE livros SET titulo=?, paginas=?, valor=? WHERE id=?"); 
            ps.setString(1,livro.getTitulo());
            ps.setInt(2,livro.getPaginas());
            ps.setFloat(3,livro.getValor());
            ps.setInt(4, livro.getId());
            if(ps.execute()){
                return true;
            } 
	}catch(SQLException e){
            System.out.println(e.getMessage());
	}
         return true;
    }
    public boolean deleteLivro(Livro livro){
        // Código para remover tupla
        boolean resultado = false;
        try{
            Conexao conectar = new Conexao(); // Executar conexão com o banco.
            PreparedStatement ps = conectar.getCon().prepareStatement("DELETE FROM livros WHERE id=?"); 
            ps.setInt(1, livro.getId());
            if(ps.execute()){
                return true;
            } 
	}catch(SQLException e){
            System.out.println(e.getMessage());
	}
        return resultado;
    }
    public ArrayList consultarLivros(){
        // Código para consultar todas as tuplas
        ArrayList lista = new ArrayList<>();
        //lista =null;
        try{
            Conexao conectar = new Conexao(); // Abre a conexão com o banco
            PreparedStatement ps = conectar.getCon().prepareStatement("SELECT * FROM livros;"); // Definir o comando que será executado
            ResultSet rs = ps.executeQuery(); // Armazena os dados no Objeto ResultSet
        if(rs != null){ // Verifica se o resultado é nulo
            while(rs.next()){ // Enquanto existir tuplas na consulta       
                Livro li = new Livro(); // Cria objeto da classe Livro
                    // Popula o Objeto
                    li.setId(rs.getInt("id"));
                    li.setTitulo(rs.getString("titulo"));
                    li.setPaginas(rs.getInt("paginas"));
                    li.setValor(rs.getFloat("valor"));
                    lista.add(li);
            }
	}else{
            lista=null;
        }
	}catch(SQLException e){
		lista = null;
	}
        return lista;
    }
    public Livro consultarLivro(int id){
        // Código para consultar uma única tupla
        Livro livro = null;
        try{
            Conexao conectar = new Conexao();
            PreparedStatement ps = conectar.getCon().prepareStatement("SELECT * FROM livros WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs != null){
                while(rs.next()){
                        livro = new Livro();
                    	livro.setId(rs.getInt("id"));
        		livro.setTitulo(rs.getString("titulo"));
                    	livro.setPaginas(rs.getInt("paginas"));
                    	livro.setValor(rs.getFloat("valor"));
                }
            }
        }catch(SQLException e){
              System.out.println(e.getMessage());
	}			
			return livro;
    }

}
// Fazer import da classe java.util.ArrayList
		