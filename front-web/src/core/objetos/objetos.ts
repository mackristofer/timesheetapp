

export type TimeSheet = {
    id: number;
    data: string | null;
    tempo: string;
    descricao: string;
    advogado: number;
    caso: number;
    cobranca: number;
}

export type Categoria = {
    id: number;
    descricao: string;
    valorHora: number;
}

export type Advogado = {
    id: number;
    nome: string;
    cpf: string;
    endereco: string;
    complemento: string;
    bairro: string;
    cidade: string;
    uf: string;
    cep: string;
    telefone: string;
    categoria: Categoria;
}

export type ClienteResponse = {
    id: number;
    razao: string;
    cnpjCpf: string;
    endereco: string;
    complemento: string;
    bairro: string;
    cidade: string;
    uf: string;
    cep: string;
    telefone: string;
    contato: string;
    obs: string;
    casos: Caso[];

}

export type Cliente = {
    id: number;
    razao: string;
    cnpjCpf: string;
    endereco: string;
    complemento: string;
    bairro: string;
    cidade: string;
    uf: string;
    cep: string;
    telefone: string;
    contato: string;
    obs: string;
    casos: Caso[];

}

export type Caso = {
    id: number;
    descricao: string;
    cliente: number;
    valor: number;
    statusCobranca: number;
    tipoContrato: number;
    desconto: number;
    //timesheets:TimeSheet[];
}

export type CasoTs = {
    id: number;
    descricao: string;
}

export type ClienteTs = {
    id: number;
    razao: string;
    casos: CasoTs[];
}

export type AdvogadoTs = {
    id: number;
    nome: string;
}

export type TimeSheetResponse = {
    id: number;
    data: string;
    tempo: string;
    descricao: string;
    advogado: Advogado;
    caso: Caso;
    cobranca: number;
}

export type SheetByAdv = {
    nomeAdv: string;
    minutos: number;
    valorHora: number;
    valorTotalHoras: number;
    timeSheet: TimeSheet[];
}

export type CasoByAdv = {
    nome: string;
    sheetByAdv: SheetByAdv[];
}

export type RelatorioData = {
    nomeCliente: string;
    casoByAdv: CasoByAdv[];
}

export type Roles = {
    id:number;
    authority:string;
}

export type Usuario = {
    id:number;
    usuario:string;
    password:string;
    advogado:AdvogadoTs;
    roles:Roles[];
}