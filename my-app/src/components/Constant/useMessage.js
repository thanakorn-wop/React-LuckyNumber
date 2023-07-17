
export const  useMessage = () => {

    const addMessages = (msgs, respStatusProcess, respMessage) => {

        msgs.current.show(
            respStatusProcess === true? { severity: 'success', summary: respMessage, sticky: true, closable: true } :
            { severity: 'error', summary: respMessage, sticky: true, closable: true }
        );

        return msgs;
    }

    return addMessages;
};

