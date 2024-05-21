/* UPX II: */


drop table Usuario;

-- Criação das tabelas

CREATE TABLE Usuario (
    Nome VARCHAR(100) NOT NULL,
    data_nascimento DATE NOT NULL,
    data_comeco_fumo DATE NOT NULL,
    ID_User INTEGER AUTO_INCREMENT NOT NULL,
    Email VARCHAR (100) NOT NULL,
    Senha varchar(100) NOT NULL,
    media_cigarro INTEGER NOT NULL,
    qtdDiasSemFumar INTEGER,
    qtdDiasVicio INTEGER,
    ContagemCigarros INTEGER,
    meta INTEGER NOT NULL,
    custo_diario DOUBLE(5,2) NOT NULL,
    nivel_dependencia INTEGER NOT NULL,
    data_de_registro timestamp DEFAULT current_timestamp,
    primary key (ID_User)
);

insert into usuario (Nome, data_nascimento, data_comeco_fumo, Email, Senha, media_cigarro, meta, custo_diario, nivel_dependencia) values ("Pedro", "2005-05-09", "2021-06-09", "teste@gmail.com", "admin", 10, 5, 10.00, 5.00);
select * from usuario;
select * from registrodiario where id_user = 15;

insert into registrodiario (nivel_desejo_fumar, cigarros_fumados, data_registro, ID_User) values (4, 10, "2024-05-15", 15);
insert into registrodiario (nivel_desejo_fumar, cigarros_fumados, data_registro, ID_User) values (4, 4, "2024-05-11", 15);

CREATE TABLE RegistroDiario (
    ID_RegistroDiario INTEGER AUTO_INCREMENT,
    nivel_desejo_fumar decimal(4,2),
    cigarros_fumados INTEGER NOT NULL,
    data_registro date DEFAULT current_timestamp() NOT NULL, 
    ID_User INTEGER NOT NULL,
    PRIMARY KEY (ID_RegistroDiario),
    FOREIGN KEY (ID_User) REFERENCES Usuario(ID_User)
);

insert into registrodiario (nivel_desejo_fumar, cigarros_fumados, data_registro, ID_User) values
(8.00, 0, '2024-05-06', 8),
(5.00, 0, '2024-05-07', 8),
(6.00, 0, '2024-05-08', 8),
(7.00, 0, '2024-05-09', 8),
(10.00, 0, '2024-05-10', 8),
(9.00, 0, '2024-05-11', 8),
(8.00, 0, '2024-05-12', 8),
(9.00, 0, '2024-05-13', 8),
(9.00, 0, '2024-05-14', 8),
(9.00, 0, '2024-05-15', 8),
(9.00, 0, '2024-05-16', 8);

select * from usuario where ID_User = 8;
select * from registrodiario where ID_User = 8;
select count(*) as total from registrodiario where data_registro = '2024-05-13' and ID_User = 8;

delete from registrodiario where ID_User = 8; 

-- Fim da criação das tabelas


-- Alteração nas tabelas

ALTER TABLE RegistroDiario MODIFY COLUMN data_registro DATE;
ALTER TABLE usuario ADD COLUMN data_de_registro timestamp DEFAULT current_timestamp;

-- Fim das alterações.

-- Inserção para teste
insert into usuario (Nome, data_nascimento, data_comeco_fumo, Email, Senha, media_cigarro, meta, custo_diario, nivel_dependencia) values ("Joaoaa", "2005-05-09", "2023-03-05", "admia12@gmail.com", "admin", 10, 0, 10.00, 5.00);
select * from usuario;
-- Fim da inserção.


-- Trigger para calcular a quantidade de dias do vício do usuário

DELIMITER //

CREATE TRIGGER calcula_qtdDiasVicio
BEFORE INSERT ON Usuario
FOR EACH ROW
BEGIN
    DECLARE qtdDias INT;

    -- Calcula a diferença de dias entre data_comeco_fumo e data_de_registro
    SET qtdDias = DATEDIFF(NEW.data_de_registro, NEW.data_comeco_fumo);

    -- Atualiza a coluna qtdDiasVicio com o número calculado de dias
    SET NEW.qtdDiasVicio = qtdDias;
END//

DELIMITER ;

-- Fim do trigger. 

drop trigger calcularContagemCigarros;
-- Trigger para calcular a quantidade de cigarros que o usuário já fumou. 

DELIMITER //

CREATE TRIGGER calcularContagemCigarros
BEFORE INSERT ON Usuario
FOR EACH ROW
BEGIN
    DECLARE dias_fumando INTEGER;
    DECLARE contagem_cigarros INTEGER;

    -- Calcula a diferença de dias entre as datas
    SET dias_fumando = DATEDIFF(NEW.data_de_registro, NEW.data_comeco_fumo);

    -- Calcula a contagem de cigarros multiplicando a média de cigarros pelo número de dias fumando
    SET contagem_cigarros = NEW.media_cigarro * dias_fumando;

    -- Atualiza o valor da ContagemCigarros na linha inserida
    SET NEW.ContagemCigarros = contagem_cigarros;
END;
//

-- Fim do trigger.

DELIMITER ;

-- Inserções de dados para teste 

insert into registrodiario (nivel_desejo_fumar, cigarros_fumados, data_registro, ID_User) values (5.00, 10, NOW(), 8);
insert into registrodiario (nivel_desejo_fumar, cigarros_fumados, data_registro, ID_User) values (1.00, 0, '2024-05-13', 2);
insert into registrodiario (nivel_desejo_fumar, cigarros_fumados, data_registro, ID_User) values (5.00, 5, '2024-05-14', 2);
insert into registrodiario (nivel_desejo_fumar, cigarros_fumados, data_registro, ID_User) values (4.00, 0, '2024-05-15', 2);

insert into registrodiario (nivel_desejo_fumar, cigarros_fumados, data_registro, ID_User) values (2.00, 5, '2024-05-16', 2);
insert into registrodiario (nivel_desejo_fumar, cigarros_fumados, data_registro, ID_User) values (2.00, 0, '2024-05-17', 2);
insert into registrodiario (nivel_desejo_fumar, cigarros_fumados, data_registro, ID_User) values (2.00, 3, '2024-05-18', 2);

select * from registrodiario where ID_User = 14;
select * from usuario where ID_User = 1;
select * from usuario;


SELECT COUNT(*) AS total FROM registrodiario WHERE cigarros_fumados = 0 and ID_User = 2;



-- Fim das inserções. 


drop trigger atualizarContagemCigarros;
-- Trigger para atualizar a quantidade de cigarros que o indivíduo já fumou. 

DELIMITER //

CREATE TRIGGER atualizarContagemCigarros
AFTER INSERT ON RegistroDiario
FOR EACH ROW
BEGIN
    -- Variável para armazenar o valor atual da ContagemCigarros
    DECLARE contagem_atual INTEGER;

    -- Obtém o valor atual da ContagemCigarros para o usuário
    SELECT ContagemCigarros INTO contagem_atual FROM Usuario WHERE ID_User = NEW.ID_User;

    -- Soma a quantidade de cigarros fumados ao valor atual da ContagemCigarros
    SET contagem_atual = contagem_atual + NEW.cigarros_fumados;

    -- Atualiza o valor da ContagemCigarros na tabela Usuario
    UPDATE Usuario SET ContagemCigarros = contagem_atual WHERE ID_User = NEW.ID_User;
END;
//

DELIMITER ;

-- Fim do trigger.

drop trigger atualizarQtdDiasVicio;
-- Trigger para atualizar a quantidade de dias sóbrio ou dependente do usuário (trigger apresentando má funcionamento). 


DELIMITER //

CREATE TRIGGER atualizarQtdDiasVicio
AFTER INSERT ON RegistroDiario
FOR EACH ROW
BEGIN
    DECLARE cigarros_fumados_qtd INTEGER;
	
    -- Obtém a quantidade de cigarros fumados do novo registro
    SET cigarros_fumados_qtd = NEW.cigarros_fumados;

    IF cigarros_fumados_qtd < 1 THEN
	
        -- Se cigarros_fumados for maior que 0, adiciona 1 a qtdDiasVicio na tabela Usuario
        UPDATE Usuario SET qtdDiasSemFumar = qtdDiasSemFumar + 1 WHERE ID_User = NEW.ID_User;
    ELSE

        -- Se cigarros_fumados for igual a 0, adiciona 1 a qtdDiasSemFumar na tabela Usuario
        UPDATE Usuario SET qtdDiasVicio = qtdDiasVicio + 1 WHERE ID_User = NEW.ID_User;
    END IF;
END;
//

DELIMITER ;


drop trigger atualizarQtdDiasSemFumar;
-- Fim do trigger. 

DELIMITER //

CREATE TRIGGER atualizarQtdDiasSemFumar
AFTER INSERT ON RegistroDiario
FOR EACH ROW
BEGIN
    DECLARE cigarros_fumados_qtd INTEGER;

    -- Obtém a quantidade de cigarros fumados do novo registro
    SET cigarros_fumados_qtd = NEW.cigarros_fumados;

    -- Adicione uma verificação simples para cigarros_fumados
    IF cigarros_fumados_qtd < 1 THEN
        -- Incrementa qtdDiasSemFumar na tabela Usuario
        UPDATE Usuario 
        SET qtdDiasSemFumar = qtdDiasSemFumar + 1 
        WHERE ID_User = NEW.ID_User;
    END IF;
END;
//

DELIMITER ;