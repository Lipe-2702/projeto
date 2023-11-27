CREATE TABLE tb_enderecos
(
    id     BIGINT AUTO_INCREMENT NOT NULL,
    rua    VARCHAR(255)          NULL,
    numero INT                   NULL,
    bairro VARCHAR(255)          NULL,
    cidade VARCHAR(255)          NULL,
    estado INT                   NULL,
    CONSTRAINT pk_tb_enderecos PRIMARY KEY (id)
);

CREATE TABLE tb_membros
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    cpf                VARCHAR(14)           NOT NULL UNIQUE ,
    nome               VARCHAR(255)          NULL,
    idade              INT                   NULL,
    data_de_nascimento date                  NULL,
    sexo               INT                   NULL,
    email              VARCHAR(255)          NULL UNIQUE,
    senha              INT                   NULL,
    ativo              BIT(1)                NULL,
    endereco_id        BIGINT                NULL,
    CONSTRAINT pk_tb_membros PRIMARY KEY (id)
);

ALTER TABLE tb_membros
    ADD CONSTRAINT uc_tb_membros_cpf UNIQUE (cpf);

ALTER TABLE tb_membros
    ADD CONSTRAINT FK_TB_MEMBROS_ON_ENDERECO FOREIGN KEY (endereco_id) REFERENCES tb_enderecos (id);

CREATE TABLE tb_igrejas
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    nome_da_igreja   VARCHAR(255)          NULL,
    cnpj             VARCHAR(255)          NOT NULL UNIQUE ,
    email            VARCHAR(255)          NULL UNIQUE,
    senha            INT                   NULL,
    data_de_fundacao date                  NULL,
    endereco_id      BIGINT                NULL,
    ativo            BIT(1)                NULL,
    CONSTRAINT pk_tb_igrejas PRIMARY KEY (id)
);

ALTER TABLE tb_igrejas
    ADD CONSTRAINT uc_tb_igrejas_cnpj UNIQUE (cnpj);

ALTER TABLE tb_igrejas
    ADD CONSTRAINT FK_TB_IGREJAS_ON_ENDERECO FOREIGN KEY (endereco_id) REFERENCES tb_enderecos (id);

CREATE TABLE tb_contas
(
    id                        BIGINT AUTO_INCREMENT NOT NULL,
    dtype                     VARCHAR(31)           NULL,
    numero_da_conta           INT                   NOT NULL UNIQUE,
    senha                     INT                   NOT NULL,
    saldo                     DECIMAL               NULL,
    data_de_abertura_da_conta date                  NULL,
    membro_id                 BIGINT                NULL,
    igreja_id                 BIGINT                NULL,
    CONSTRAINT pk_tb_contas PRIMARY KEY (id)
);

ALTER TABLE tb_contas
    ADD CONSTRAINT uc_tb_contas_numerodaconta UNIQUE (numero_da_conta);

ALTER TABLE tb_contas
    ADD CONSTRAINT FK_TB_CONTAS_ON_IGREJA FOREIGN KEY (igreja_id) REFERENCES tb_igrejas (id);

ALTER TABLE tb_contas
    ADD CONSTRAINT FK_TB_CONTAS_ON_MEMBRO FOREIGN KEY (membro_id) REFERENCES tb_membros (id);