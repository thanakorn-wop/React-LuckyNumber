
export const  useMessage = () => {

    const addMessages = (msgs, respStatusCode, respMessage) => {

        msgs.current.show(
            respStatusCode === 200? { severity: 'success', summary: respMessage, sticky: true, closable: true } :
            { severity: 'error', summary: respMessage, sticky: true, closable: true }
        );

        return msgs;
    }

    return addMessages;
};

