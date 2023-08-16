
export const  useMessage = () => {

    const addMessages = (msgs, statusMessage, respMessage) => {

        msgs.current.show(
            statusMessage === 'success'? { severity: 'success', summary: respMessage, sticky: true, closable: true } :
            statusMessage === 'warning'?  { severity: 'warn', summary: respMessage, sticky: true, closable: true }:
            { severity: 'error', summary: respMessage, sticky: true, closable: true }
        );

        return msgs;
    }

    return addMessages;
};

